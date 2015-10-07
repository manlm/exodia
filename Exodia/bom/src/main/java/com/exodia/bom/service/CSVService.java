package com.exodia.bom.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by manlm1 on 9/21/2015.
 */

@Service
public class CSVService {

    private static final Logger LOG = Logger.getLogger(CSVService.class);

    /**
     * Eport CSV file
     *
     * @param fileName
     * @param header
     * @param content
     * @param response
     */
    public void exportCSV(String fileName, StringBuilder header, StringBuilder content, HttpServletResponse response) {

        LOG.info(new StringBuilder("[exportCSV] Start: fileName = ").append(fileName).append(", header = ")
                .append(header).append(", content = ").append(content));

        OutputStream outputStream = null;
        try {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", String.valueOf(new StringBuilder("attachment; filename=").
                    append(fileName).append(".csv")));
            outputStream = response.getOutputStream();
            outputStream.write(String.valueOf(header).getBytes());
            outputStream.write(String.valueOf(content).getBytes());
        } catch (IOException e) {
            LOG.error(new StringBuilder("[exportCSV] IOException: ").append(e.getMessage()));
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                LOG.error(new StringBuilder("[exportCSV] IOException: ").append(e.getMessage()));
            }
            LOG.info("[exportCSV] End");
        }
    }
}
