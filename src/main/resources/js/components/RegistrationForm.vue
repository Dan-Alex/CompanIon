<template>

    <div>
        <form action="/index" method="post">
            <label for="username">Имя пользователя:</label>
                <input name="username" type="text" v-model="user.username"><br/>
            <label for="password">Пароль:</label>
                <input name="password" type="text" v-model="user.password"><br/>
            <label for="name">Имя:</label>
                <input name="name" type="text" v-model="user.name"><br/>
            <label for="surname">Фамилия</label>
                <input type="text" v-model="user.surname"><br/>
            <label for="surname">Должность</label>
                <input name="position" type="text" v-model="user.position"><br/>
            <label for="department">Отдел</label>
                <input name="department" type="text" v-model="user.departmentName"><br/>
            <label for="email">Почта:</label>
                <input name="email" type="text" v-model="user.email"><br/>
            <label for="phone">Телефон:</label>
                <input name="phone" type="text" v-model="user.phone"><br/>
            <input @click="registerUser()" type="submit" value="Зарегестрировать">
        </form>
    </div>

</template>

<script>
    import {mapActions} from 'vuex'

    export default {

        props: ['userAttr'],

        data() {
            return {
                    user : {
                        username: '',
                        name: '',
                        surname: '',
                        password: '',
                        position: '',
                        email: '',
                        phone: '',
                        departmentName: ''
                    }
                    }
                },
        watch: {
            userAttr(user) {
                this.user = user
            }
            },

        methods:{
            ...mapActions(['addUserAction', 'updateUserAction']),

            registerUser() {
                const saveUser = this.user;
                if (saveUser.id) {
                    this.updateUserAction(saveUser)
                }
                    else
                    {
                        this.addUserAction(saveUser)
                    }
                Object.keys(this.user).forEach( key => this.user[key] = '')
            }
        }

    }
</script>

<style scoped>

</style>