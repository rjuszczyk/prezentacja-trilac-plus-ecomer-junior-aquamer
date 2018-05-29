package pl.pharmaway.prezentacjatrilac.network;

public class SendResponse {
    int status;

    public boolean isSuccess() {
        return status == 1;
    }
}
