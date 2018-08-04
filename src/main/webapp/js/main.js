Vue.http.options.emulateJSON = true;
Vue.http.options.crossOrigin = true;
Vue.component('qr-image-dialog', {
    data: function () {
        return {
            imageSrc: '',
            message: ''
        }
    },
    template: '#qr-image-dialog',
    methods: {
        toggle: function () {
            $('#qr_image_modal').modal('toggle');
        }
    }
});

Vue.component('qr-pay-panel', {
    data: function () {
        return {
            merId: '',
            storeCode: '',
            outTradeNo: '',
            orderAmt: '',
            tradeDate: '',
            tradeTime: '',
            payExpire: '',
            tporderCreateIp: '',
            notifyFlagInput: '',
        }
    },
    template: '#qrPay',
    methods: {
        postDefault: function () {
            this.merId = '020002040095';
            this.storeCode = '02000015087';
            this.outTradeNo = 'ZHL777O15002039';
            this.orderAmt = '7370';
            this.tradeDate = '20171210';
            this.tradeTime = '160346';
            this.payExpire = '1200';
            this.tporderCreateIp = '127.0.0.1';
            this.notifyFlagInput = '1';
            this.post();
        },
        post: function () {
            Pace.restart();
            this.$http.post('http://localhost:8080/icbc/qr/getQrCode', {
                'merId': this.merId,
                'storeCode': this.storeCode,
                'outTradeNo': this.outTradeNo,
                'orderAmt': this.orderAmt,
                'tradeDate': this.tradeDate,
                'tradeTime': this.tradeTime,
                'payExpire': this.payExpire,
                'tporderCreateIp': this.tporderCreateIp,
                'notifyFlag': this.notifyFlagInput
            }, {
                'headers': {
                    'Access-Control-Allow-Origin': '*'
                }
            }).then(function (response) {
                var respText = response.bodyText;
                var respJson = JSON.parse(respText);
                if (respJson.status === "0") {
                    this.$refs.qr_image_dialog.imageSrc = respJson.data;
                    this.$refs.qr_image_dialog.message = '';
                    console.log(respJson)
                } else {
                    this.$refs.qr_image_dialog.imageSrc = '';
                    this.$refs.qr_image_dialog.message = respJson.msg;
                }
                this.$refs.qr_image_dialog.toggle();
            }, function (response) {
                console.log(response)
            });
        }
    }

});

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
        path: '/library/:part',
        component: {
            template: '#library'
        }
    },
    {
        path: '/library/:part/:section',
        component: {
            template: '#library'
        }
    },
    {
        path: '/library/商户收单服务/二维码扫码支付',
        component: {
            template: ''
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
    data: {
        isAccessibilityModel: false,
        accessibilityModel: '无障碍模式',
        contentFontSize: 1
    },
    router: router,
    methods: {
        fullScreen: function () {
            screenfull.toggle();
            this.isAccessibilityModel = !this.isAccessibilityModel;
            this.changeAccessibilityModel();
        },
        changeAccessibilityModel: function () {
            if (this.isAccessibilityModel) {
                this.accessibilityModel = '一般模式';
                this.contentFontSize = 3;
            } else {
                this.accessibilityModel = '无障碍模式';
                this.contentFontSize = 1;
            }
        }
    }
});

