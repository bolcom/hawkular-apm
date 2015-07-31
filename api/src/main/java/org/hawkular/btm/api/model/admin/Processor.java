/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hawkular.btm.api.model.admin;

import java.util.ArrayList;
import java.util.List;

import org.hawkular.btm.api.model.btxn.NodeType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class represents a processor, used to extract information from the instrumented
 * location, to derive properties, correlation ids and contents to be stored in the
 * business transaction fragment/nodes.
 *
 * @author gbrown
 */
public class Processor {

    @JsonInclude
    private NodeType nodeType;

    @JsonInclude
    private boolean request;

    @JsonInclude
    private String uriFilter;

    @JsonInclude(Include.NON_NULL)
    private String operation;

    @JsonInclude(Include.NON_NULL)
    private String faultFilter;

    @JsonInclude
    private List<ProcessorAction> actions = new ArrayList<ProcessorAction>();

    /**
     * @return the nodeType
     */
    public NodeType getNodeType() {
        return nodeType;
    }

    /**
     * @param nodeType the nodeType to set
     */
    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * @return the request
     */
    public boolean isRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(boolean request) {
        this.request = request;
    }

    /**
     * @return the uriFilter
     */
    public String getUriFilter() {
        return uriFilter;
    }

    /**
     * @param uriFilter the uriFilter to set
     */
    public void setUriFilter(String uriFilter) {
        this.uriFilter = uriFilter;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return the faultFilter
     */
    public String getFaultFilter() {
        return faultFilter;
    }

    /**
     * @param faultFilter the faultFilter to set
     */
    public void setFaultFilter(String faultFilter) {
        this.faultFilter = faultFilter;
    }

    /**
     * @return the actions
     */
    public List<ProcessorAction> getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(List<ProcessorAction> actions) {
        this.actions = actions;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Processor [nodeType=" + nodeType + ", request=" + request + ", uriFilter=" + uriFilter
                + ", operation=" + operation + ", faultFilter=" + faultFilter + ", actions=" + actions + "]";
    }

}
