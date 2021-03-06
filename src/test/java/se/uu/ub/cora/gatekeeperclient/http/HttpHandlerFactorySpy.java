/*
 * Copyright 2016 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.uu.ub.cora.gatekeeperclient.http;

import java.util.ArrayList;
import java.util.List;

import se.uu.ub.cora.httphandler.HttpHandler;
import se.uu.ub.cora.httphandler.HttpHandlerFactory;
import se.uu.ub.cora.httphandler.HttpMultiPartUploader;

public class HttpHandlerFactorySpy implements HttpHandlerFactory {

	public List<HttpHandlerSpy> factored = new ArrayList<>();
	private int status = 200;

	public HttpHandlerSpy getFactored(int i) {
		return factored.get(i);
	}

	@Override
	public HttpHandler factor(String url) {
		HttpHandlerSpy httpHandlerSpy = new HttpHandlerSpy();
		factored.add(httpHandlerSpy);
		httpHandlerSpy.setURL(url);
		httpHandlerSpy.setResponseCode(status);
		String jsonAnswer = "{\"children\":[{\"children\":[{\"children\":["
				+ "{\"name\":\"role\",\"value\":\"someRole1\"}],\"name\":\"rolePlus\"}"
				+ ",{\"children\":[{\"name\":\"role\",\"value\":\"someRole2\"}]"
				+ ",\"name\":\"rolePlus\"}],\"name\":\"rolesPlus\"}],\"name\":\"someId2\"}";
		httpHandlerSpy.setResponseText(jsonAnswer);
		return httpHandlerSpy;
	}

	public void setResponseCode(int status) {
		this.status = status;
	}

	@Override
	public HttpMultiPartUploader factorHttpMultiPartUploader(String url) {
		// TODO Auto-generated method stub
		return null;
	}

}
