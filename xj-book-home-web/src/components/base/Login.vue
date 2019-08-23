<style lang="less">
#login_all{
  display: flex;
  align-content: flex-start
}
</style>

<template>
  <div id="login_all">
    <Form ref="form" :model="form" :rules="rules" inline>
      <FormItem prop="username">
        <Input type="text" v-model="form.username" placeholder="请输入用户名">
          <Icon type="ios-person-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem prop="password">
        <Input type="password" v-model="form.password" placeholder="请输入密码">
          <Icon type="ios-lock-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="login">登录</Button>
      </FormItem>
    </Form>
  </div>
</template>

<script>
  import {doLogin} from "../../api/login";

  export default {
    name: "Login",
    data() {
      return {
        form: {
          username: '',
          password: '',
        },
        rules: {
          username: [
            {required: true, message: '请输入用户名', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
          ]
        }
      }
    },
    created() {
    },
    methods: {
      login(){
        this.$refs['form'].validate(async (valid) => {
          if (valid) {
            const result =await doLogin(this.form);
            if(result.status===200){
              this.$Message.info(JSON.stringify(result));
            }else{
              this.$Message.error(result.body);
            }
          }
        });
      },
    },
  }
</script>

