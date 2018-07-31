
Vue.http.options.emulateJSON = true;
Vue.http.options.crossOrigin = true;

var routes = [
    {
        path: '/',
        component: {
            template: '#home'
        }
    },
    {
        path: '/library',
        component: {
            template: '#library'
        }
    },
    {
        path: '/about',
        component: {
            template: '#about'
        }
    }
];

var router = new VueRouter({
    routes: routes
});

var vm = new Vue({
    el: '#app',
    router: router,
    mounted: function () {
        console.log(router)
    }
});


var qr_image_dialog = new Vue({
    el: '#qr-image-dialog',
    data: {
        imageSrc: '',
        message: ''
    },
});
