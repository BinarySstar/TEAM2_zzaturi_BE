package goorm.zzaturi.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReIssueRequest {

    @NotBlank
    private String refreshToken;
}
