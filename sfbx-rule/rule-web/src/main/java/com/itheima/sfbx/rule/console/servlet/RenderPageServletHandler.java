/*******************************************************************************
 * Copyright 2017 Bstek
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.itheima.sfbx.rule.console.servlet;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.itheima.sfbx.framework.rule.Utils;


/**
 * @author Jacky.gao
 * @since 2016年6月6日
 */
public abstract class RenderPageServletHandler extends WriteJsonServletHandler implements ApplicationContextAware{
	protected VelocityEngine ve;
	protected ApplicationContext applicationContext;
	protected String buildProjectNameFromFile(String file) {
		String project=null;
		if(StringUtils.isNotBlank(file)){
			file=Utils.decodeURL(file);
			if(file.startsWith("/")){
				file=file.substring(1,file.length());
				int pos=file.indexOf("/");
				project=file.substring(0,pos);
			}
		}
		return project;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
		ve = new VelocityEngine();
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM,new NullLogChute());
		ve.init();	
	}
}
