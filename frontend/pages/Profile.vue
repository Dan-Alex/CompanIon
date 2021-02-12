<template>
    <div>
        <h2>Мой профиль</h2>
        <p> {{getProfile.name}} {{getProfile.surname}} </p>
        <p> Должность: {{getProfile.position}}</p>
        <p> Отдел: {{getProfile.departmentName}}</p>
        <p> Почта: {{getProfile.email}}</p>
        <p> Тел.: {{getProfile.phone}}</p>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'

    export default {

        computed: mapGetters(['getProfile', 'getRoles']),
    }
</script>

<style scoped>

    h2 {
        text-align: center;
    }
</style>