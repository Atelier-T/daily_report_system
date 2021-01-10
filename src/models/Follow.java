package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "follows")

// ネームドクエリの候補
// 全取得、フォローユーザの人数、

@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private Employee follower_id;

    @ManyToOne
    @JoinColumn(name = "followed_id", nullable = false)
    private Employee followed_id;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(Employee follower_id) {
        this.follower_id = follower_id;
    }

    public Employee getFollowed_id() {
        return followed_id;
    }

    public void setFollowed_id(Employee followed_id) {
        this.followed_id = followed_id;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}