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
package com.itheima.sfbx.framework.rule.runtime.agenda;

import com.itheima.sfbx.framework.rule.action.ActionValue;
import com.itheima.sfbx.framework.rule.model.rule.Rule;
import com.itheima.sfbx.framework.rule.model.rule.RuleInfo;
import com.itheima.sfbx.framework.rule.runtime.rete.EvaluationContext;

import java.util.List;

/**
 * @author Jacky.gao
 * @since 2015年1月2日
 */
public interface RuleBox{
	List<RuleInfo> execute(AgendaFilter filter,int max,List<ActionValue> actionValues);
	boolean add(Activation activation);
	RuleBox next();
	List<Rule> getRules();
	void reevaluate(Object obj,EvaluationContext context);
	void retract(Object obj);
	void clean();
}
