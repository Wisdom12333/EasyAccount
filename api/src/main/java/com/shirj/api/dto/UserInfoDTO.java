package com.shirj.api.dto;

import com.shirj.api.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    private long userId;

    private String username;

    private List<Account> accounts;
}
