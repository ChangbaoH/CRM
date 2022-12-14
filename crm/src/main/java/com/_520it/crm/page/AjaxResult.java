package com._520it.crm.page;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AjaxResult {
    private boolean success;
    private String msg;

    public AjaxResult() {
    }

    public AjaxResult(String msg) {
        this.msg = msg;
    }

    public AjaxResult(Boolean success) {
        this.success = success;
    }

    public AjaxResult(boolean success, String msg) {

        this.success = success;
        this.msg = msg;
    }
}
