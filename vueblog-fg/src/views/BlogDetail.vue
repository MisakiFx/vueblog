<template>
    <div>
        <Header></Header>
        <el-container>
            <el-main>
                <div class="m-blog">
                    <p class="m-header-title">{{ blog.title }}</p>
                    <router-link :to="{name: 'BlogEdit',params:{blogId: blog.id}}">
                        <el-button style="float: left;" type="primary" plain icon="el-icon-edit" circle v-if="ownBlog"></el-button>
                    </router-link>
                    <el-button @click="del(blog.id)" style="float: left;" type="danger" plain icon="el-icon-delete" circle v-if="ownBlog"></el-button>
                    <div>
                        <el-divider><i class="el-icon-edit"></i></el-divider>
                    </div>
                    <div class="markdown-body" v-html="blog.content"></div>
                </div>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import Header from "../components/Header";
    import 'github-markdown-css';
    import Footer from "../components/Footer";
    export default {
        name: "BlogDetail",
        components:{Footer, Header},
        data(){
            return{
                blog:{
                    id: '',
                    title:'',
                    content:''
                },
                islogin:false,
                ownBlog:false
            }
        },
        created() {
            const blogId = this.$route.params.blogId
            console.log(blogId)
            const _this = this
            if(blogId){
                this.$axios.get("/blog/"+blogId).then(res=>{
                    const blog = res.data.data
                    _this.blog.id = blog.id
                    _this.blog.title = blog.title
                    var MarkdownIt = require('markdown-it');
                    var md = new MarkdownIt();
                    var result = md.render(blog.content);
                    _this.blog.content = result
                    _this.islogin = (blog.userId === _this.$store.getters.getUserInfo.id)
                    _this.ownBlog = (blog.userId === _this.$store.getters.getUserInfo.id)
                })
            }
        }
    }
</script>

<style scoped>
    /*文章内容页面样式*/
    .m-blog{
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        width: 1100px;
        min-height: 600px;
        margin: 0 auto;
        padding: 20px 40px;
    }
    /*文章标题样式*/
    .m-header-title{
        text-align: center;
        font-size:30px;
        font-family: 'Dubai Light';
        padding-top: 25px;
    }
    /*文章内容样式*/
    .markdown-body{
        margin-top: 120px;
    }
</style>