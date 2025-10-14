package com.doansamquoc.wemoment.moment.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.doansamquoc.wemoment.common.enums.MediaType;
import com.doansamquoc.wemoment.common.enums.MomentPrivacy;
import com.doansamquoc.wemoment.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Table(name = "moments")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Moment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    User user;

    String caption;
    @Enumerated(EnumType.STRING)
    MediaType mediaType = MediaType.IMAGE;
    String mediaUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    MomentPrivacy privacy = MomentPrivacy.PUBLIC;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
