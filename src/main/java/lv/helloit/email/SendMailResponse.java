package lv.helloit.email;

import java.util.Objects;

public class SendMailResponse {

    private boolean successful;
    private String errorMessage;

    private SendMailResponse(Builder builder) {
        successful = builder.successful;
        errorMessage = builder.errorMessage;
    }


    @Override
    public String toString() {
        return "SendMailResponse{" +
                "successful=" + successful +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendMailResponse that = (SendMailResponse) o;
        return successful == that.successful &&
                Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(successful, errorMessage);
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static final class Builder {
        private boolean successful;
        private String errorMessage;

        public Builder() {
        }

        public Builder successful(boolean val) {
            successful = val;
            return this;
        }

        public Builder errorMessage(String val) {
            errorMessage = val;
            return this;
        }

        public SendMailResponse build() {
            return new SendMailResponse(this);
        }
    }
}
