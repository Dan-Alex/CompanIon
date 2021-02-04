<template>
    <div id="user" @click="click(user)">
        {{user.name}} {{user.surname}} {{user.position}} {{user.departmentName}} <br/>
        e-mail: {{user.email}} тел.: {{user.phone}} <br/>
        <div v-if="getRoles.includes('ROLE_ADMIN')">
            <input type="button" value="Удалить" @click="deleteUser(user)"/>
            <router-link :to="{name : 'registration', params: {user: user}}">Изменить</router-link> <br/>
        </div>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";

    export default {

        props: {
            user:{
                type: Object
            }
        },

        computed: {
          ...mapGetters(['getRoles'])
        },

        methods: {
            ...mapActions(['deleteUserAction']),

            deleteUser(user) {
                this.deleteUserAction(user);
            },
            click(user){
                this.$root.$emit('selectUser', user)
            }
        },
    }
</script>

<style scoped>

</style>