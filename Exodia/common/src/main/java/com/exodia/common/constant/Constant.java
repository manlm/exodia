package com.exodia.common.constant;

/**
 * Created by manlm1 on 9/12/2015.
 */
public class Constant {

    public enum ADMIN_ROLE {
        ACCOUNT_MANAGER("ACCOUNT MANAGER"), DATA_MANAGER("DATA MANAGER");

        private String value;

        public String getValue() {
            return value;
        }

        private ADMIN_ROLE(String value) {
            this.value = value;
        }
    }

    public enum ADMIN_ROLE_ID {
        ACCOUNT_MANAGER(1), DATA_MANAGER(2);

        private int value;

        public int getValue() {
            return value;
        }

        private ADMIN_ROLE_ID(int value) {
            this.value = value;
        }
    }

    public enum STATUS {
        ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), DELETED("DELETED");

        private String value;

        public String getValue() {
            return value;
        }

        private STATUS(String value) {
            this.value = value;
        }
    }

    public enum STATUS_ID {
        ACTIVE(1), INACTIVE(2), DELETED(7);

        private int value;

        public int getValue() {
            return value;
        }

        private STATUS_ID(int value) {
            this.value = value;
        }
    }
}
