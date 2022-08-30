package com.example.cashregister.Service.extra;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

import java.io.IOException;

import static com.example.cashregister.Service.extra.Notifications.*;

public class Tag implements SimpleTag {
    JspContext jspContext;
    public void doTag() throws IOException {
        String getMess=getMessage();
        String getERMess=getErrormessage();
        if (getMess != null) {
            jspContext.getOut().print("<span style=\" color: #e7f608 \"><b>"+getMess+"</b></span>");
        } else if(getERMess!=null){
            jspContext.getOut().print("<span style=\" color: #8a0d0d \"><b>"+getERMess+"</b></span>");
        }
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