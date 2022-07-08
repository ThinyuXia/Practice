<template>
    <ContentBase>
        <div class="row">
            <div class="col-3">
                <UserProfileInfo @follow="follow" @unfollow="unfollow" :user="user"></UserProfileInfo>
                <UserProfileWrite @post_a_post="post_a_post"></UserProfileWrite>
            </div>
            <di class="col-9">
                <UserProfilePosts :posts="posts"></UserProfilePosts>
            </di>
        </div>
    </ContentBase>

</template>

<script>
import ContentBase from '../components/ContentBase';
import UserProfileInfo from '../components/UserProfileInfo';
import UserProfilePosts from '../components/UserProfilePosts';
import UserProfileWrite from '../components/UserProfileWrite'
import { reactive } from 'vue';

export default {
    name: 'UserProfile',
    components: {
        ContentBase,
        UserProfileInfo,
        UserProfilePosts,
        UserProfileWrite
    },
    setup() {
        const user = reactive({
            id: 1,
            username: "xiaxinyu",
            lastName: "Xia",
            firstName: "Xinyu",
            followerCount: 999,
            is_followed: true
        });

        const posts = reactive({
            count: 3,
            posts: [
                {
                    id: 1,
                    userId: 1,
                    content: "1111111",
                },
                {
                    id: 2,
                    userId: 1,
                    content: "2222222",
                },
                {
                    id: 3,
                    userId: 1,
                    content: "3333333",
                },
            ]
        })

        const follow = () => {
            if (user.is_followed) return;
            user.is_followed = true;
            user.followerCount++;
        };

        const unfollow = () => {
            if (!user.is_followed) return;
            user.is_followed = false;
            user.followerCount--;
        };

        const post_a_post = (content) => {
            posts.count++;
            posts.posts.unshift({
                id: posts.count,
                userId: 1,
                content: content,
            })
        }

        return {
            user,
            follow,
            unfollow,
            posts,
            post_a_post
        }
    },

}
</script>

<style>
</style>
