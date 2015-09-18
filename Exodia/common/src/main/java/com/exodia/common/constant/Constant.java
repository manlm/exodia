package com.exodia.common.constant;

/**
 * Created by manlm1 on 9/12/2015.
 */
public class Constant {

    public enum ADMIN_ROLE {
        ACCOUNT_MANAGER("ACCOUNT_MANAGER"), DATA_MANAGER("DATA_MANAGER");

        private String value;

        private ADMIN_ROLE(String value) {
            this.value = value;
        }
    }

    public enum ADMIN_ROLE_ID {
        ACCOUNT_MANAGER(1), DATA_MANAGER(2);

        private int value;

        private ADMIN_ROLE_ID(int value) {
            this.value = value;
        }
    }

    public enum STATUS {
        ACTIVE(1), INACTIVE(2);

        private int value;

        private STATUS(int value) {
            this.value = value;
        }
    }
}
