package com.shirj.api.dto;

import com.shirj.api.core.entity.BaseEntity;
import com.shirj.api.entity.Account;
import com.shirj.api.entity.Trade;
import lombok.*;

import java.util.List;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private List<Account> accounts;

    private List<Trade> recentTrade;

    private Integer expend;

    private Integer income;
}
