/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.nsc.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.common.collect.Lists;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.osgi.service.component.annotations.Reference;

import javax.annotation.PostConstruct;
import javax.jcr.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavModel {

    @ValueMapValue
    private String text;

    @SlingObject
    private Resource self;

    private List<String> list;

    @ChildResource
    private List<Item> multifield;


    @PostConstruct
    public void init() {
        list = Lists.newArrayList();
        Resource multifield = self.getChild("multifield");

        multifield.getChildren().forEach(r -> list.add(r.getValueMap().get("title", String.class)));

    }

    @Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
            defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class Item{
        @ValueMapValue
        private String title;

        public String getTitle() {
            return title;
        }
    }

    public String getText() {
        return text;
    }

    public List<String> getList() {
        return list;
    }

    public List<Item> getMultifield() {
        return multifield;
    }
}