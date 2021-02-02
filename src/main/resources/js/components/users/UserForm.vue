<template>

    <div>
        <h1 v-if="isUpdate">Изменить информацию пользователя</h1>
        <h1 v-if="!isUpdate">Зарегестрировать пользователя</h1>
        <form action="/employees" method="post">
            <label for="username">Имя пользователя:</label>
                <input name="username" type="text" v-model="user.username"><br/>
            <div v-show="!isUpdate">
                <label for="password">Пароль:</label>
                    <input name="password" type="text" v-model="user.password"><br/>
            </div>
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
            <input v-if="!isUpdate" @click="registerUser()" type="submit" value="Зарегестрировать">
            <input v-if="isUpdate" @click="updateUser()" type="submit" value="Изменить">
        </form>
    </div>

</template>

<script>
    import {mapActions} from 'vuex'

    export default {

        props: {
            userAttr: {
                type: Object
            }
        },

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
                    },
                    isUpdate: false

            }
        },

        created() {
            if (this.userAttr){
                this.user = this.userAttr;
                this.isUpdate = true;
            }
        },

        methods:{
            ...mapActions(['addUserAction', 'updateUserAction']),

            registerUser() {
                        this.addUserAction(this.user)
                        Object.keys(this.user).forEach( key => this.user[key] = '')
            },

            updateUser() {
                        this.updateUserAction(this.user);
                        Object.keys(this.user).forEach( key => this.user[key] = '')
                        this.isUpdate = false;
            }
        }

    }
</script>

<style scoped>

</style>