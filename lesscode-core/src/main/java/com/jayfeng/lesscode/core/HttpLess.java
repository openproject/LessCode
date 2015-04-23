package com.jayfeng.lesscode.core;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;

public class HttpLess {

    private final static String QUERY_ENCODING = "UTF-8";
    private static ExecutorService mExecutorService = Executors.newFixedThreadPool(4);

    public static String $get(String url) {
        InputStream is = null;
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setUseCaches(false);
            conn.setConnectTimeout($.sConnectTimeOut);
            conn.setReadTimeout($.sReadTimeout);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpStatus.SC_OK) {
                is = conn.getInputStream();
                return FileLess.$read(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void $get(final String url, final CallBack callBack) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                String result = $get(url);
                callBack.onFinish(result);
            }
        });
    }

    public static String $post(String url, Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return $get(url);
        }
        OutputStream os = null;
        InputStream is = null;
        StringBuffer body = joinParam(params);
        byte[] data = body.toString().getBytes();
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setConnectTimeout($.sConnectTimeOut);
            conn.setReadTimeout($.sReadTimeout);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            conn.setDoOutput(true);
            os = conn.getOutputStream();
            os.write(data);
            if (conn.getResponseCode() == HttpStatus.SC_OK) {
                is = conn.getInputStream();
                return FileLess.$read(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void $post(final String url, final Map<String, String> params, final CallBack callBack) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                String result = $post(url, params);
                callBack.onFinish(result);
            }
        });
    }

    public static long $download(String downloadUrl, File dest, boolean append, DownloadCallBack callBack) throws Exception {
        int progress = 0;
        long remoteSize = 0;
        int currentSize = 0;
        long totalSize = -1;

        if (!append && dest.exists() && dest.isFile()) {
            dest.delete();
        }

        if (append && dest.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(dest);
                currentSize = fis.available();
            } catch (IOException e) {
                throw e;
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        }

        HttpGet request = new HttpGet(downloadUrl);

        if (currentSize > 0) {
            request.addHeader("RANGE", "bytes=" + currentSize + "-");
        }

        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, $.sConnectTimeOut);
        HttpConnectionParams.setSoTimeout(params, 40000);
        HttpClient httpClient = new DefaultHttpClient(params);

        InputStream is = null;
        FileOutputStream os = null;
        try {
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                is = response.getEntity().getContent();
                remoteSize = response.getEntity().getContentLength();
                Header contentEncoding = response.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    is = new GZIPInputStream(is);
                }
                os = new FileOutputStream(dest, append);
                byte buffer[] = new byte[8192];
                int readSize = 0;
                while ((readSize = is.read(buffer)) > 0) {
                    os.write(buffer, 0, readSize);
                    os.flush();
                    totalSize += readSize;
                    if (callBack != null) {
                        progress = (int) (totalSize * 100 / remoteSize);
                        callBack.onDownloading(progress);
                    }
                }

                if (progress != 100) {
                    callBack.onDownloading(100);
                }

                if (totalSize < 0) {
                    totalSize = 0;
                }
            }
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }

        if (totalSize < 0) {
            throw new Exception("Download file fail: " + downloadUrl);
        }

        if (callBack != null) {
            callBack.onDownloaded();
        }

        return totalSize;

    }

    public static String $upload(String url, Map<String, String> params, Map<String, File> files) {
        String BOUNDARY = UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";

        HttpURLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;

        try {
            URL uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setReadTimeout($.sReadTimeout);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

            // text parameters
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
                sb.append("Content-Type: text/plain; charset=GBK" + LINEND);
                sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                sb.append(LINEND);
                sb.append(entry.getValue());
                sb.append(LINEND);
            }

            os = new DataOutputStream(conn.getOutputStream());
            os.write(sb.toString().getBytes());
            // send file data
            if (files != null)
                for (Map.Entry<String, File> file : files.entrySet()) {
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(PREFIX);
                    sb1.append(BOUNDARY);
                    sb1.append(LINEND);
                    sb1.append("Content-Disposition: form-data; name=\"uploadfile\"; filename=\""
                            + file.getValue().getName() + "\"" + LINEND);
                    sb1.append("Content-Type: application/octet-stream; charset=GBK" + LINEND);
                    sb1.append(LINEND);
                    os.write(sb1.toString().getBytes());

                    is = new FileInputStream(file.getValue());
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                    is.close();
                    os.write(LINEND.getBytes());
                }

            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            os.write(end_data);
            os.flush();
            StringBuilder sb2 = new StringBuilder();
            if (conn.getResponseCode() == HttpStatus.SC_OK) {
                InputStream in = conn.getInputStream();
                return FileLess.$read(in);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void $upload(final String url, final Map<String, String> params, final Map<String, File> files, final CallBack callBack) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                String result = $upload(url, params, files);
                callBack.onFinish(result);
            }
        });
    }

    private static StringBuffer joinParam(Map<String, String> params) {
        StringBuffer result = new StringBuffer();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> param = iterator.next();
            String key = param.getKey();
            String value = param.getValue();
            result.append(key).append('=').append(value);
            if (iterator.hasNext()) {
                result.append('&');
            }
        }
        return result;
    }

    public interface CallBack {
        public void onFinish(String result);
    }

    public interface DownloadCallBack {
        public void onDownloading(int progress);

        public void onDownloaded();
    }
}
