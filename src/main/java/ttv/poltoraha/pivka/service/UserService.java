package ttv.poltoraha.pivka.service;

import ttv.poltoraha.pivka.entity.MyUser;

public interface UserService {
    public void updatePassword(MyUser user, String NewPassword);
}
