package com.tr.shopping.core.utils;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forDto();
    ModelMapper forRequest();

}
