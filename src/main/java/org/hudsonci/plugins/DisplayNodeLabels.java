package org.hudsonci.plugins;

import hudson.Extension;
import hudson.model.Computer;
import hudson.node_monitors.AbstractNodeMonitorDescriptor;
import hudson.node_monitors.NodeMonitor;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

public class DisplayNodeLabels extends NodeMonitor {
    @Extension
    public static final AbstractNodeMonitorDescriptor<Data> DESCRIPTOR = new AbstractNodeMonitorDescriptor<Data>() {
        protected Data monitor(Computer c) throws IOException, InterruptedException {
                return new Data(c);
        }

        public String getDisplayName() {
            return "Node monitor";
        }

        @Override
        public NodeMonitor newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            return new DisplayNodeLabels();
        }
    };
    
    @ExportedBean
    public static final class Data {
        Computer c;

        public Data(Computer c) {
                this.c = c;
        }

        @Exported
        public String getLabels() {
                return c.getNode().getLabelString();
        }

        public String toString() {
                return getLabels();
        }
    }
}


