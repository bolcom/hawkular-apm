/*
 * Copyright 2015-2016 Red Hat, Inc. and/or its affiliates
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
package org.hawkular.btm.api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.hawkular.btm.api.model.trace.CorrelationIdentifier;
import org.hawkular.btm.api.model.trace.CorrelationIdentifier.Scope;
import org.junit.Test;

/**
 * @author gbrown
 */
public class CriteriaTest {

    @Test
    public void testGetQueryParametersNoCriteria() {
        Criteria criteria = new Criteria();

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);
        assertTrue("Empty map expected", queryParameters.isEmpty());
    }

    @Test
    public void testGetQueryParametersStartTime() {
        Criteria criteria = new Criteria();
        criteria.setStartTime(100);

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);
        assertEquals("100", queryParameters.get("startTime"));
    }

    @Test
    public void testGetQueryParametersEndTime() {
        Criteria criteria = new Criteria();
        criteria.setEndTime(200);

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);
        assertEquals("200", queryParameters.get("endTime"));
    }

    @Test
    public void testGetQueryParametersSingleProperties() {
        Criteria criteria = new Criteria();
        criteria.addProperty("prop1", "value1", false);

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);

        assertTrue(queryParameters.containsKey("properties"));

        assertEquals("prop1|value1", queryParameters.get("properties"));
    }

    @Test
    public void testGetQueryParametersSinglePropertiesExcluded() {
        Criteria criteria = new Criteria();
        criteria.addProperty("prop1", "value1", true);

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);

        assertTrue(queryParameters.containsKey("properties"));

        assertEquals("-prop1|value1", queryParameters.get("properties"));
    }

    @Test
    public void testGetQueryParametersMultipleProperties() {
        Criteria criteria = new Criteria();
        criteria.addProperty("prop1", "value1", false);
        criteria.addProperty("prop2", "value2", false);

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);

        assertTrue(queryParameters.containsKey("properties"));

        assertTrue(queryParameters.get("properties").equals("prop1|value1,prop2|value2")
                || queryParameters.get("properties").equals("prop2|value2,prop1|value1"));
    }

    @Test
    public void testGetQueryParametersMultiplePropertiesExcluded() {
        Criteria criteria = new Criteria();
        criteria.addProperty("prop1", "value1", true);
        criteria.addProperty("prop2", "value2", true);

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);

        assertTrue(queryParameters.containsKey("properties"));

        assertTrue(queryParameters.get("properties").equals("-prop1|value1,-prop2|value2")
                || queryParameters.get("properties").equals("-prop2|value2,-prop1|value1"));
    }

    @Test
    public void testGetQueryParametersMultipleSameNameProperties() {
        Criteria criteria = new Criteria();
        criteria.addProperty("prop1", "value1", false);
        criteria.addProperty("prop1", "value2", false);

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);

        assertTrue(queryParameters.containsKey("properties"));

        assertTrue(queryParameters.get("properties").equals("prop1|value1,prop1|value2")
                || queryParameters.get("properties").equals("prop1|value2,prop1|value1"));
    }

    @Test
    public void testGetQueryParametersMultipleSameNamePropertiesExcluded() {
        Criteria criteria = new Criteria();
        criteria.addProperty("prop1", "value1", true);
        criteria.addProperty("prop1", "value2", true);

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);

        assertTrue(queryParameters.containsKey("properties"));

        assertTrue(queryParameters.get("properties").equals("-prop1|value1,-prop1|value2")
                || queryParameters.get("properties").equals("-prop1|value2,-prop1|value1"));
    }

    @Test
    public void testGetQueryParametersSingleCorrelations() {
        Criteria criteria = new Criteria();

        CorrelationIdentifier id1 = new CorrelationIdentifier();
        id1.setScope(Scope.Global);
        id1.setValue("value1");

        criteria.getCorrelationIds().add(id1);

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);

        assertTrue(queryParameters.containsKey("correlations"));

        assertEquals("Global|value1", queryParameters.get("correlations"));
    }

    @Test
    public void testGetQueryParametersMultipleCorrelations() {
        Criteria criteria = new Criteria();

        CorrelationIdentifier id1 = new CorrelationIdentifier();
        id1.setScope(Scope.Global);
        id1.setValue("value1");

        criteria.getCorrelationIds().add(id1);

        CorrelationIdentifier id2 = new CorrelationIdentifier();
        id2.setScope(Scope.Interaction);
        id2.setValue("value2");

        criteria.getCorrelationIds().add(id2);

        Map<String, String> queryParameters = criteria.parameters();

        assertNotNull(queryParameters);

        assertTrue(queryParameters.containsKey("correlations"));

        assertTrue(queryParameters.get("correlations").equals("Global|value1,Interaction|value2")
                || queryParameters.get("correlations").equals("Interaction|value2,Global|value1"));
    }

}
