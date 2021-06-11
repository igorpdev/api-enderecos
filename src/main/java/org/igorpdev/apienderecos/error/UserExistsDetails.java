package org.igorpdev.apienderecos.error;

public class UserExistsDetails extends DefaultExceptionDetails {
    public static final class Builder{
        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public UserExistsDetails build() {
            UserExistsDetails userExistsDetails = new UserExistsDetails();
            userExistsDetails.setDeveloperMessage(developerMessage);
            userExistsDetails.setTitle(title);
            userExistsDetails.setDetail(detail);
            userExistsDetails.setTimestamp(timestamp);
            userExistsDetails.setStatus(status);
            return userExistsDetails;
        }
    }
}
