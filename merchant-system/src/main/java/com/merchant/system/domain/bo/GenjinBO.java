package com.merchant.system.domain.bo;

import com.merchant.system.domain.Genjin;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname GenjinBO
 * @Description TODO
 * @Date 2020/10/29 14:58
 * @Created by hanke
 */
@Data
public class GenjinBO extends Genjin {

    private MultipartFile img;

}
