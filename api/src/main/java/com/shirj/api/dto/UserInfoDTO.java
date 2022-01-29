package com.shirj.api.dto;

import com.shirj.api.entity.Account;
import com.shirj.api.entity.Trade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    private Long userId;

    private String username;

    private List<Account> accounts;

    private List<Trade> recentTrade;

    private Double expend;

    private Double income;
}
