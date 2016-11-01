package com.mstring.andtest.volley;

import com.android.volley.VolleyLog;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;

/**
 * Created by 李宗源 on 2016/10/28.
 */

public class FileUploadEntity implements HttpEntity {

    private static final String TAG = FileUploadEntity.class.getSimpleName();

    private static final String BOUNDARY = "__FILE_UPLOAD_ENTITY__";

    private ByteArrayOutputStream mOutputStream;

    public FileUploadEntity() {
        mOutputStream = new ByteArrayOutputStream();

        try {
            writeFirstBoundary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFirstBoundary() throws IOException {
        VolleyLog.e("writeFirstBoundary");
        mOutputStream.write(("--" + BOUNDARY + "\r\n").getBytes());
        mOutputStream.write(("Content-Disposition: form-data; name=\"" + "name" + "\"\r\n\r\n").getBytes());
        mOutputStream.write("Content-Transfer-Encoding: binary\n\n".getBytes());
        mOutputStream.flush();
    }


    private void writeLastBoundary() throws IOException {
        VolleyLog.e("writeLastBoundary");
        mOutputStream.write(("\r\n--" + BOUNDARY + "--\r\n").getBytes());
    }

    public void addFile(final String key, final File file) {
        VolleyLog.e("addFile");
        InputStream inputStream = null;

        try {
            mOutputStream.write(("\r\n--" + BOUNDARY + "\r\n").getBytes());

            StringBuilder stringBuilderContentDisposition = new StringBuilder();
            stringBuilderContentDisposition.append("Content-Disposition: ");
            stringBuilderContentDisposition.append("form-data; ");
            stringBuilderContentDisposition.append("name=\"" + key + "\"; ");
            stringBuilderContentDisposition.append("filename=\"" + file.getName() + "\"\r\n");
            mOutputStream.write(stringBuilderContentDisposition.toString().getBytes());

            StringBuilder stringBuilderContentType = new StringBuilder();
            stringBuilderContentType.append("Content-Type: ");
            stringBuilderContentType.append(new MimetypesFileTypeMap().getContentType(file).toString());
            stringBuilderContentType.append("\r\n\r\n");
            mOutputStream.write(stringBuilderContentType.toString().getBytes());

            inputStream = new FileInputStream(file);
            final byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                VolleyLog.e("len --> %s", String.valueOf(len));
                mOutputStream.write(buffer, 0, len);
            }
            VolleyLog.e("===last====len --> %s", String.valueOf(len));

            mOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSilently(inputStream);
        }
    }

    private void closeSilently(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public boolean isChunked() {
        return false;
    }

    @Override
    public long getContentLength() {
        return mOutputStream.toByteArray().length;
    }

    @Override
    public Header getContentType() {
        return new BasicHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
    }

    @Override
    public Header getContentEncoding() {
        return null;
    }

    @Override
    public InputStream getContent() throws IOException, UnsupportedOperationException {
        return new ByteArrayInputStream(mOutputStream.toByteArray());
    }

    @Override
    public void writeTo(OutputStream outputStream) throws IOException {
        writeLastBoundary();
        outputStream.write(mOutputStream.toByteArray());
    }

    @Override
    public boolean isStreaming() {
        return false;
    }

    @Override
    public void consumeContent() throws IOException {

    }
}
