package com.dynatrace.diagnostics.plugins.jmx.variableholder;

import java.util.*;

public class DSTimeHolder {

    private ArrayList<DSStats> holder = new ArrayList<DSStats>();

    public synchronized ArrayList<DSStats> getArrayList() {
        return holder;
    }

    public synchronized void setArrayList(ArrayList<DSStats> obj) throws Exception {
        holder = obj;
    }

    public synchronized void addServerStats(DSStats parm) {
        holder.add(parm);
    }

    public synchronized void addServerStatsList(ArrayList<DSStats> obj) {
        holder.addAll(obj);
    }

    public void Clearvalue() throws Exception {
        holder.clear();
    }
}