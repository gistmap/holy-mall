package com.gistmap.common.filter;

import okio.ByteString;
import okio.Okio;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
    private ByteString cachedBytes;

    public MultiReadHttpServletRequest(ServletRequest request) {
        super((HttpServletRequest) request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (cachedBytes == null)
            cacheInputStream();

        return new CachedServletInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    private void cacheInputStream() throws IOException {
        cachedBytes = Okio.buffer(Okio.source(super.getInputStream())).readByteString();
    }

    public class CachedServletInputStream extends ServletInputStream {
        private ByteArrayInputStream input;

        public CachedServletInputStream() {
            input = new ByteArrayInputStream(cachedBytes.toByteArray());
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public int read() throws IOException {
            return input.read();
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }
}
