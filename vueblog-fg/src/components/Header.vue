<template>
    <div>
        <el-container>
            <el-header>
                <div>
                    <el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                        <el-menu-item index="4">
                            <router-link :to="{name: 'Index'}">主页</router-link>
                        </el-menu-item>
                        <el-menu-item index="4">
                            <router-link :to="{name: 'BlogAdd'}">发表</router-link>
                        </el-menu-item>
                        <!-- 归档,分类,友链,留言:待开发的新功能 -->
                        <el-menu-item index="4">
                            <span><el-link>归档</el-link></span>
                        </el-menu-item>
                        <el-menu-item index="4">
                            <span><el-link>分类</el-link></span>
                        </el-menu-item>
                        <el-menu-item index="4">
                            <span><el-link>友链</el-link></span>
                        </el-menu-item>
                        <el-menu-item index="4">
                            <span><el-link>留言</el-link></span>
                        </el-menu-item>
                        <div class="m-user-info">
                            <el-submenu index="2">
                                <template slot="title">
                                    <img :src="user.avatar" width="30px"/> {{user.username}}
                                </template>
                                <el-menu-item index="2-1">
                                    <span v-show="!islogin"><el-link type="success" @click="login">登录</el-link></span>
                                    <span v-show="islogin"><el-link type="danger" @click="logout">注销</el-link></span>
                                </el-menu-item>
                            </el-submenu>
                        </div>
                    </el-menu>
                </div>
            </el-header>
            <el-main class="m-content">
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import Element from 'element-ui';
    export default {
        name: "Header",
        data() {
            return {
                user: {
                    username: '未登录',
                    avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
                },
                islogin: false
            }
        },
        methods: {
            logout() {
                const _this = this
                _this.$axios.get("/logout", {
                    headers: {
                        "Authorization": localStorage.getItem("token")
                    }
                }).then(res => {
                    _this.$store.commit("REMOVE_INFO")
                    Element.Message.info("logout success!");
                    _this.$router.push("/login")
                })
            },
            login(){
                const _this = this
                _this.$router.push("/login")
            }
        },
        created() {
            if(this.$store.getters.getUserInfo.username){
                this.user.username = this.$store.getters.getUserInfo.username
                this.user.avatar = this.$store.getters.getUserInfo.avatar
                this.islogin = true
            }
        }
    }
</script>

<style scoped>
    /*定义header样式宽度并居中*/
    .m-content {
        margin-top: 50px;
        text-align: center;
    }
    /*导航栏样式*/
    .el-menu-demo{
        margin-top: 0px;
    }
    /*导航栏中用户信息样式*/
    .m-user-info{
        float: right;
    }
    /*清除router-link标签生成的下划线等样式*/
    a {
        text-decoration: none;
    }
    .router-link-active {
        text-decoration: none;
    }
</style>