<template>

    <div>
        <h2 v-if="isUpdate">Изменить информацию пользователя</h2>
        <h2 v-if="!isUpdate">Зарегестрировать пользователя</h2>
           <div class="form__wrapper">
                <ul>
                    <li>
                        <label for="username">Имя пользователя:</label>
                            <input name="username" type="text" v-model="user.username">
                    </li>

                    <li v-if="!isUpdate">
                        <label for="password">Пароль:</label>
                            <input name="password" type="password" v-model="user.password"><br/>
                    </li>

                    <li v-if="!isUpdate">
                        <label for="confirmPassword">Подтвердите пароль:</label>
                        <input name="confirmPassword" type="password" v-model="confirmPassword"><br/>
                    </li>

                    <li>
                        <label for="name">Имя:</label>
                            <input name="name" type="text" v-model="user.name">
                    </li>

                    <li>
                        <label for="surname">Фамилия:</label>
                            <input type="text" v-model="user.surname">
                    </li>

                    <li>
                        <label for="surname">Должность:</label>
                            <input name="position" type="text" v-model="user.position">
                    </li>

                    <li>
                        <label for="department">Отдел:</label>
                        <input name="department" type="text" v-model="user.departmentName"><br/>
                    </li>
                        <find-department class="find__department" v-if="user.departmentName === ''"></find-department>

                    <li>
                        <label for="email">Почта:</label>
                            <input name="email" type="text" v-model="user.email">
                    </li>

                    <li>
                        <label for="phone">Телефон:</label>
                        <input name="phone" type="text" v-model="user.phone">
                    </li>

                    <li class="errorMessage">
                        <ul>
                            <li v-for="errorMessage in errorMessages">{{errorMessage}}</li>
                        </ul>
                    </li>

                    <li v-if="!isUpdate">
                        <input class="submit" @click="registerUser()" type="submit" value="Зарегестрировать">
                    </li>

                    <li v-if="isUpdate">
                        <input class="submit" @click="updateUser()" type="submit" value="Изменить">
                    </li>
                </ul>
           </div>
    </div>

</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import FindDepartment from '../departments/findDepartment.vue'

    export default {

        props: {
            userAttr: {
                type: Object
            }
        },

        components: {
            FindDepartment
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
                    isUpdate: false,
                    confirmPassword: '',
                    errorMessages: []
            }
        },

        created() {
            if (this.userAttr){
                this.user = this.userAttr;
                this.isUpdate = true;
            }

            this.$root.$on('selectDepartment', (selectedDepartment) => {
                this.user.departmentName = selectedDepartment.name});
        },

        computed: mapGetters(['getUsers']),

        methods:{
            ...mapActions(['addUserAction', 'updateUserAction']),

            registerUser() {
                        this.errorMessages = [];

                        if (this.getUsers.find(user => user.username === this.user.username) !== undefined)
                            this.errorMessages.push("Пользователь с таким именем уже существует");

                        if (this.user.password !== this.confirmPassword)
                            this.errorMessages.push("Пароли не совпадают");

                        if (this.errorMessages.length === 0) {
                            this.addUserAction(this.user);
                            Object.keys(this.user).forEach(key => this.user[key] = '');
                            this.confirmPassword = '';
                        }


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

    h2 {
        text-align: center;
    }

    .form__wrapper {
        width: 800px;
        margin: 20px auto;
    }

    ul {
        list-style-type: none;
    }

    li {
        display: flex;
        justify-content: flex-end;
        padding: 4px;
    }

    li > label {
        padding: 8px 16px 8px 0;
        flex: 1;
    }

    li > input {
        flex: 2;
        padding: 8px;
    }

    .errorMessage {
        color: red;
    }

    .submit {
        border: 5px;
        padding: 8px;
        background-color: coral;
        font-size: 16pt;
        cursor: pointer;
    }
</style>