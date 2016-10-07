package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class ServletTimeHolder {

    private ArrayList<ServletStats> holder = new ArrayList<ServletStats>();

    public synchronized ArrayList<ServletStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<ServletStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(ServletStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<ServletStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}
