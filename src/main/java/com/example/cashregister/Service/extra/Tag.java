package com.example.cashregister.Service.extra;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

import java.io.IOException;

public class Tag implements SimpleTag {
    JspContext jspContext;
    public void doTag() throws IOException {
        jspContext.getOut().print("<span style=\"color: green;\">Cash Register</span>");
    }

    @Override
    public void setParent(JspTag jspTag) {

    }

    @Override
    public JspTag getParent() {
        return null;
    }

    @Override
    public void setJspContext(JspContext jspContext) {
        this.jspContext = jspContext;
    }

    @Override
    public void setJspBody(JspFragment jspFragment) {

    }
}