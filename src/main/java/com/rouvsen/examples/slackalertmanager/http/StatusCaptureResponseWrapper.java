package com.rouvsen.examples.slackalertmanager.http;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class StatusCaptureResponseWrapper extends HttpServletResponseWrapper {
    private int httpStatus = SC_OK;

    public StatusCaptureResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void setStatus(int sc) {
        this.httpStatus = sc;
        super.setStatus(sc);
    }

    @Override
    public void sendError(int sc) throws IOException {
        this.httpStatus = sc;
        super.sendError(sc);
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        this.httpStatus = sc;
        super.sendError(sc, msg);
    }

    public int getStatus() {
        return this.httpStatus;
    }
}

