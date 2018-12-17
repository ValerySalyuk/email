package lv.helloit.email;


import java.util.Objects;

public class SendMailRequest {

    private String to;
    private String subject;
    private String body;
    private boolean isHtml;

    private SendMailRequest(Builder builder) {
        to = builder.to;
        subject = builder.subject;
        body = builder.body;
        isHtml = builder.isHtml;
    }


//    public SendMailRequest(String to, String subject, String body, boolean isHtml) {
//        this.to = to;
//        this.subject = subject;
//        this.body = body;
//        this.isHtml = isHtml;
//    }

    @Override
    public String toString() {
        return "SendMailRequest{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", isHtml=" + isHtml +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendMailRequest that = (SendMailRequest) o;
        return isHtml == that.isHtml &&
                Objects.equals(to, that.to) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, subject, body, isHtml);
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public boolean isHtml() {
        return isHtml;
    }


    public static final class Builder {
        private String to;
        private String subject;
        private String body;
        private boolean isHtml;

        public Builder() {
        }

        public Builder to(String val) {
            to = val;
            return this;
        }

        public Builder subject(String val) {
            subject = val;
            return this;
        }

        public Builder body(String val) {
            body = val;
            return this;
        }

        public Builder isHtml(boolean val) {
            isHtml = val;
            return this;
        }

        public SendMailRequest build() {
            return new SendMailRequest(this);
        }
    }
}
