package com.dotto.app.faq.dto;

import com.dotto.app.config.constants.FaqType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "FAQ 변경 요청")
@Data
@NoArgsConstructor
public class FaqUpdateRequest {
    @ApiModelProperty(value = "구분", notes = "구분을 입력한다" , example = "전체" ,required = true)
    @NotBlank
    private String category;

    @ApiModelProperty(value = "문의내용", notes = "문의내용을 입력한다", example = "회원가입은 어떻게 하나요?", required = true)
    @NotBlank
    private String qContent;

    @ApiModelProperty(value = "답변내용", notes = "답변내용을 입력한다", example = "회원가입은 이렇게 합니다", required = true)
    @NotBlank
    private String aContent;
}
