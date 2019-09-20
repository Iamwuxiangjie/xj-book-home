import gulp from "gulp";
import gulpif from "gulp-if";
import {colors, log} from "gulp-util";
import named from "vinyl-named";
import gulpWebpack from "webpack-stream";
import UglifyJsPlugin from 'uglifyjs-webpack-plugin'
import plumber from "gulp-plumber";
import livereload from "gulp-livereload";
import args from "./lib/args";
import {BundleAnalyzerPlugin} from "webpack-bundle-analyzer";

const ENV = args.production ? 'production' : 'development';

gulp.task('scripts', (cb) => {
  return gulp.src([
    'app/scripts/*.js',
  ])
    .pipe(plumber({
      errorHandler: function () {
        // Webpack will log the errors
      }
    }))
    .pipe(named())
    .pipe(gulpWebpack({
      devtool: args.sourcemaps ? 'inline-source-map' : false,
      watch: args.watch,
      plugins: [].concat(args.production ? [
        new UglifyJsPlugin()
      ] : []).concat(args.analyze ? [
        new BundleAnalyzerPlugin()
      ] : []),
      module: {
        rules: [{
          test: /\.js$/,
          use: 'babel-loader'
        }, {
          test: /\.vue$/,
          use: 'vue-loader'
        }, {
          test: /\.css$/,
          use: ["style-loader", "css-loader"]
        }, {
          test: /\.(eot|svg|ttf|woff|woff2)(\?\S*)?$/,
          use: [{
            loader: 'file-loader',
            query: {
              name: '../styles/[name].[ext]'
            }
          }],
        }, {
          test: /\.(png|jpe?g|gif)(\?\S*)?$/,
          use: [{
            loader: 'file-loader',
            query: {
              name: '../images/[name].[ext]'
            }
          }]
        }, {
          test: /\.json$/,
          use: [{
            loader: 'file-loader',
            query: {
              name: '../scripts/[name].[ext]'
            }
          }]
        }]
      },
      resolve: {
        alias: {
          'vue$': 'vue/dist/vue.js'
        }
      }
    }, null, (err, stats) => {
      log(`Finished '${colors.cyan('scripts')}'`, stats.toString({
        chunks: false,
        colors: true,
        cached: false,
        children: false
      }));
    }))
    .pipe(gulp.dest("src/main/resources/static/scripts"))
    .pipe(gulp.dest("target/classes/static/scripts"))
    .pipe(gulpif(args.watch, livereload({
      port: 7772
    })))

});
