var exec = require('cordova/exec');

module.exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'Projection', 'coolMethod', [arg0]);
};

module.exports.add = function (arg0, success, error) {
    exec(success, error, 'Projection', 'add', [arg0]);
};

module.exports.calcProjection = function (arg0, success, error) {
    exec(success, error, 'Projection', 'calcProjection', [arg0]);
};

module.exports.calcProjection14_15 = function (arg0, success, error) {
    exec(success, error, 'Projection', 'calcProjection14_15', [arg0]);
};

module.exports.calcAnnuityPayment = function (arg0, success, error) {
    exec(success, error, 'Projection', 'calcAnnuityPayment', [arg0]);
};

module.exports.calcCashFlow = function (arg0, success, error) {
    exec(success, error, 'Projection', 'calcCashFlow', [arg0]);
};

module.exports.calcProjection24 = function (arg0, success, error) {
    exec(success, error, 'Projection', 'calcProjection24', [arg0]);
};