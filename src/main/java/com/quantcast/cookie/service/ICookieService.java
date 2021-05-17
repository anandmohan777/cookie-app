package com.quantcast.cookie.service;

import com.quantcast.cookie.exception.ServiceException;
import com.quantcast.cookie.model.Input;

public interface ICookieService {
    void process(final Input input) throws ServiceException;
}
