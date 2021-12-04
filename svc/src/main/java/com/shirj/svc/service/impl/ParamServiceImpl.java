package com.shirj.svc.service.impl;

import com.shirj.api.dao.ParamDAO;
import com.shirj.api.service.IParamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * The implement of {@code IParamService}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Service
public class ParamServiceImpl implements IParamService {

    @Resource
    private ParamDAO paramDAO;
}
