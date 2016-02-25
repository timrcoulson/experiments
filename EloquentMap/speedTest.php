<?php

namespace JustPark;

use Illuminate\Database\Eloquent\Collection;
use PHPUnit_Framework_TestCase;

class mappingTest extends PHPUnit_Framework_TestCase
{
    public function testEloquentVsForeach()
    {
        // error_log because I haven't mastered this damn language.
        $i = 1;
        error_log('Size,Native,Laravel');
        while($i < 1000000) {
            $collection = self::generateCollection($i);
            $start = microtime(true);
            $mappedCollection1 = $collection->map(function($item) {
                return self::callback($item);
            });
            $laraLength = microtime(true) - $start;

            $start = microtime(true);
            $mappedCollection2 = self::forEech($collection);
            $nativeLength = microtime(true) - $start;
            error_log($i . ',' . $nativeLength . ',' . $laraLength);
            $this->assertEquals($mappedCollection1, $mappedCollection2);
            $i *= 2;
        }
    }

    public static function generateCollection($size)
    {
        $collection = new Collection();
        $i = 0;
        while($i < $size) {
            $item = [
                'id' => $i,
                'p1' => 'hello',
                'p2' => rand(),
            ];
            $collection->add($item);
            $i++;
        }
        return $collection;
    }

    public static function forEech(Collection $collection)
    {
        $mapped = [];
        foreach($collection as $item) {
            $mapped[] = self::callback($item);
        }
        return new Collection($mapped);
    }

    public static function callback($item) {
        return [
            'id' => $item['p2'],
            'p1' => $item['id']
        ];
    }

    /**
     * Creates the application.
     *
     * Needs to be implemented by subclasses.
     *
     * @return \Symfony\Component\HttpKernel\HttpKernelInterface
     */
    public function createApplication()
    {
        // TODO: Implement createApplication() method.
    }
}