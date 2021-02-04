<template>

    <div>
       <user-form :userAttr="user"></user-form>
    </div>

</template>

<script>
    import UserForm from "../components/users/UserForm.vue"
    export default {

        props: ['user'],

        components:{
            UserForm
        }
    }
</script>

<style scoped>

</style>