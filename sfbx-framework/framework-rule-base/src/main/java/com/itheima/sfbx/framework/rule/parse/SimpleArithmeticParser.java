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
package com.itheima.sfbx.framework.rule.parse;

import com.itheima.sfbx.framework.rule.model.rule.ArithmeticType;
import com.itheima.sfbx.framework.rule.model.rule.SimpleArithmetic;
import com.itheima.sfbx.framework.rule.model.rule.SimpleArithmeticValue;
import org.dom4j.Element;

/**
 * @author Jacky.gao
 * @since 2014年12月23日
 */
public class SimpleArithmeticParser implements Parser<SimpleArithmetic> {
	public SimpleArithmetic parse(Element element) {
		SimpleArithmetic arithmetic=new SimpleArithmetic();
		ArithmeticType arithmeticType=ArithmeticType.valueOf(element.attributeValue("type"));
		arithmetic.setType(arithmeticType);
		SimpleArithmeticValue value=new SimpleArithmeticValue();
		value.setContent(element.attributeValue("value"));
		arithmetic.setValue(value);
		for(Object obj:element.elements()){
			if(obj==null || !(obj instanceof Element)){
				continue;
			}
			Element ele=(Element)obj;
			if(this.support(ele.getName())){
				value.setArithmetic(this.parse(ele));
				break;
			}
		}
		return arithmetic;
	}
	public boolean support(String name) {
		return name.equals("simple-arith");
	}
}
