/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.riking.auto.commmon.event.listener;



/**
 * @Description SpringApplication 整个运行阶段各个时期的监听器
 * @Author: kongLiuYi
 * @Date: 2020/2/16 17:21
 */
public interface ETLApplicationRunListener {

	/**
	 * Called immediately when the run method has first started. Can be used for very
	 * early initialization.
	 */
	default void starting() {
	}



	/**
	 * Called once the application context has been loaded but before it has been
	 * refreshed
	 */
	default void contextLoaded() {
	}



	/**
	 * Called immediately before the run method finishes, when the application context has
	 */
	default void running() {
	}



}
