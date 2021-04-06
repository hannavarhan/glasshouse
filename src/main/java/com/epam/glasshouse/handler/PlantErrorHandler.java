package com.epam.glasshouse.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class PlantErrorHandler extends DefaultHandler {

    private final static Logger logger = LogManager.getLogger(PlantErrorHandler.class);

    @Override
    public void warning(SAXParseException e) {
        logger.warn(getErrorLocation(e) + " : " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        logger.error(getErrorLocation(e) + " : " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) {
        logger.fatal(getErrorLocation(e) + " : " + e.getMessage());
    }

    private String getErrorLocation(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
