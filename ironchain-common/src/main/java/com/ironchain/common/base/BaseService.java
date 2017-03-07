package com.ironchain.common.base;

import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public abstract class BaseService{
}
