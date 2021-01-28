package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "follows")
@NamedQueries({
    @NamedQuery(
        name = "getFollowersCount",
        query = "select count(f) from Follow as f where f.follower_id = :follower_id"
    ),
    @NamedQuery(
        name = "getFollowedCount",
        query = "select count(f) from Follow as f where f.followed_id = :follower_id"
    ),
    @NamedQuery(
        name = "tableFollow",
        query = "select f from Follow as f where f.follower_id = :follower_id"
    )
})

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
}
